import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import styled from 'styled-components';

const NoteContainer = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem;
    min-width: 12rem;
    max-width: 12rem;
    height: auto;
    border: 1px solid gray;
    height: auto;
    gap: 1rem;
    margin: 0.5rem;
    margin-top: 1.5rem;

    & span {
      word-break: break-all;
      text-align: start;
    }

    & .delete-icon:hover {
      cursor: pointer;
      transition: 1s;
      opacity: 0.9;
    }
`

const NoteCard = (props) => {

  return (
    <NoteContainer>
      <span>{props.note}</span>
      <span className='delete-icon' onClick={(event) => props.handleDeleteNote(event, props.id)}>
        <FontAwesomeIcon icon={faTrash} />
      </span>
    </NoteContainer>
  )
}

export default NoteCard;