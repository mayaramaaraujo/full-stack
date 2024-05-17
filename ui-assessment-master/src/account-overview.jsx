import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHardHat } from '@fortawesome/free-solid-svg-icons';
import styled from 'styled-components';

import './account-overview.css';
import NoteForm from './components/NoteForm';
import NoteCard from './components/NoteCard';

const NotesContainer = styled.div`
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
    max-width: 80%
`

export const AccountOverview = ({ data }) => {
  const [notes, setNotes] = useState([]);
  const [inputValue, setInputValue] = useState();
  const [shouldReload, setShouldReload] = useState(false);

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  }

  const loadNotes = async () => {
    try {
      const response = await fetch('http://localhost:8080/v1/notes', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (!response.ok) {
        throw new Error('It was not possible send the data');
      }

      const data = await response.json();
      setNotes(data);

    } catch (error) {
      console.error(error);
    }
  };

  const handleDeleteNote = async (event, id) => {
    event.preventDefault();

    try {
      const response = await fetch(`http://localhost:8080/v1/notes/${id}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (!response.ok) {
        throw new Error('It was not possible delete the note');
      }

      setShouldReload(!shouldReload);
    } catch (error) {
      console.error(error);
    }
  };

  const handleAddNote = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/v1/notes', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ note: inputValue })
      });

      if (!response.ok) {
        throw new Error('It was not possible send the data');
      }

      setInputValue('');
      setShouldReload(!shouldReload);

    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    loadNotes();
    setInputValue('')
  }, [shouldReload])

  return (
    <>
      <div className="AccountOverview">
        <FontAwesomeIcon icon={faHardHat} />
      </div>
      <div className='NotesOverview'>
        <NoteForm handleAddNote={handleAddNote} handleInputChange={handleInputChange} inputValue={inputValue} />
        <NotesContainer>
          {notes && notes.map((note) => {
            return <NoteCard key={note.id} id={note.id} note={note.note} handleDeleteNote={handleDeleteNote} />
          })}
        </NotesContainer>
      </div>
    </>
  )
}

export default AccountOverview;